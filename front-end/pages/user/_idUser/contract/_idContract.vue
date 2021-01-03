<template>
  <v-container>
    <!-- when loading : progress-bar -->
    <CustomProgressBar v-if="isLoading" message="Loading the contract's data..." />

    <div v-else>
      <!-- Buttons for modifications -->
      <ButtonsChanges
        :is-modifying="isModifying"
        text-activate="Modify the Contract"
        :activate="openChanges"
        :discard="discardChanges"
        :validate="saveChanges"
      />

      <v-card shaped>
        <!-- contract's name -->
        <h1 class="text-center">
          {{ isNewContract ? 'New contract' : 'Contract n° ' + contract.idContract }}
        </h1>

        <!-- Divider -->
        <v-divider class="ma-8" />

        <!-- Form for the contract's data -->
        <v-form ref="form" v-model="form">
          <v-container>
            <v-row justify="center">
              <!-- contract's price -->
              <v-col cols="12" sm="4">
                <v-text-field
                  v-model="contractPlaceholder.price"
                  label="Price"
                  :rules="[rules.required, rules.notNegative]"
                  :disabled="!isModifying"
                  clearable
                  class="ma-4"
                  prepend-icon="mdi-currency-usd"
                  type="number"
                />
              </v-col>

              <!-- contract's electrical consumption -->
              <v-col cols="12" sm="4">
                <v-text-field
                  v-model="contractPlaceholder.electricalConsumption"
                  label="Electrical consumption"
                  :rules="[rules.required, rules.notNegative]"
                  :disabled="!isModifying"
                  clearable
                  class="ma-4"
                  prepend-icon="mdi-flash"
                  type="number"
                />
              </v-col>

              <!-- contract's date -->
              <v-col cols="12" sm="4" align-self="center">
                <v-menu
                  ref="menuDate"
                  v-model="menuDate"
                  :close-on-content-click="false"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="contractPlaceholder.date"
                      label="Date of creation"
                      prepend-icon="mdi-calendar"
                      :disabled="!isModifying"
                      readonly
                      :rules="[rules.required]"
                      v-bind="attrs"
                      v-on="on"
                    />
                  </template>
                  <v-date-picker
                    ref="picker"
                    v-model="contractPlaceholder.date"
                    :max="new Date().toISOString().substr(0, 10)"
                    min="1950-01-01"
                    @change="save"
                  />
                </v-menu>
              </v-col>
            </v-row>
          </v-container>
        </v-form>
      </v-card>
    </div>
  </v-container>
</template>

<script>
// Imports
import ButtonsChanges from '@/components/buttons-changes'
import CustomProgressBar from '@/components/custom-progress-bar'
import MixinRules from '@/mixins/mixin-rules'
import axios from 'axios'
const lodash = require('lodash')

export default {
  name: 'PageContractEdit',

  components: {
    ButtonsChanges,
    CustomProgressBar
  },

  mixins: [MixinRules],

  data: () => ({
    // Whether the page is loading data
    isLoading: true,

    // Whether the contract is modifiying data
    isModifying: false,

    // Whether the form is valid or not
    form: false,
    menuDate: false,

    // The contract's data and the placeholder
    contract: {
      idContract: -1,
      price: 0,
      electricalConsumption: 0,
      date: null,
      user_idUser: {
        idUser: -1
      }
    },
    contractPlaceholder: {
      idContract: -1,
      price: 0,
      electricalConsumption: 0,
      date: null,
      user_idUser: {
        idUser: -1
      }
    }
  }),

  computed: {
    /** Return whether this page is for a new contract or not */
    isNewContract () {
      return parseInt(this.$route.params.idContract) === 0
    },

    /** return the id of the user */
    idUser () {
      return parseInt(this.$route.params.idUser) || undefined
    },

    /** Return the id of the contract, if he has one */
    idContract () {
      return parseInt(this.$route.params.idContract) || undefined
    }
  },

  watch: {
    menuDate (val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
    }
  },

  async mounted () {
    // If the contract is new : allow to modify immediately
    // Otherwise : get his data
    if (this.isNewContract) {
      // We activate the modification
      this.isModifying = true

      // We set the id of the user
      this.contract.user_idUser.idUser = this.idUser
    } else {
      // We get the contract's values
      const tmpContract = await axios.get('/api/contracts/' + this.idContract)

      // If a contract is found
      if (tmpContract !== undefined) {
        // We get its data
        this.contract = {
          idContract: tmpContract.data.idContract,
          price: tmpContract.data.price,
          electricalConsumption: tmpContract.data.electricalConsumption,
          date: tmpContract.data.date,
          user_idUser: {
            idUser: this.idUser
          }
        }
      }
    }

    // We set the placeholder
    this.contractPlaceholder = lodash.cloneDeep(this.contract)

    // We consider the loading done
    this.isLoading = false
  },

  methods: {
    /** Required method to save the date */
    save (date) {
      this.$refs.menuDate.save(date)
    },

    /** Opens all changes brought to the contract */
    openChanges () {
      // We open the modifications
      this.isModifying = true
    },

    /** Discards all changes brought to the contract */
    discardChanges () {
      // We reset the placeholder
      this.contractPlaceholder = lodash.cloneDeep(this.contract)

      // We close the modifications
      this.isModifying = false
    },

    /** Saves all changes brought to the contract */
    saveChanges () {
      // If the form is valid
      if (this.$refs.form.validate()) {
        // If this is a new contract : we add him to the database
        // Otherwise : we update his row in the database
        if (this.isNewContract) {
          axios.post('/api/contracts/', this.contractPlaceholder)
            .then((result) => {
              // We close the modifications
              this.isModifying = false

              // To ensure that the user does not touch anything, we consider that we are loading
              this.isLoading = true

              // We redirect toward the home page
              document.location.href = `/user/${result.data.user_idUser.idUser}/contract/${result.data.idContract}`
            }).catch(() => {
            })
        } else {
          axios.put('/api/contracts/' + this.idContract, this.contractPlaceholder)
            .then((response) => {
              // We close the modifications
              this.isModifying = false

              // We copy the placeholder into the "actual" values
              this.contract = lodash.cloneDeep(this.contractPlaceholder)
            })
            .catch(() => {
            })
        }
      }
    }
  },

  head () {
    return { title: (this.isNewContract) ? 'new contract' : this.contract.idContract }
  }
}
</script>
