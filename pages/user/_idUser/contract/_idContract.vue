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
                  label="First name"
                  :rules="[rules.required]"
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
                  label="Last name"
                  :rules="[rules.required]"
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
      date: null
    },
    contractPlaceholder: {
      idContract: -1,
      price: 0,
      electricalConsumption: 0,
      date: null
    }
  }),

  computed: {
    /** Return whether this page is for a new contract or not */
    isNewContract () {
      return parseInt(this.$route.params.idContract) === 0
    },

    /** Return the id of the contract, if he has one */
    idContract () {
      return (this.iscontract) ? undefined : parseInt(this.$route.params.idContract)
    },

    /** return the id of the user */
    idUser () {
      return (this.isUser) ? undefined : parseInt(this.$route.params.idUser)
    }
  },

  watch: {
    menuDate (val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
    }
  },

  async mounted () {
    // Blablabla, we set the contract with HARD CODED VALUES
    this.contract = {
      idContract: this.idContract,
      price: 10,
      electricalConsumption: 10,
      date: null
    }

    if (!this.isNewContract) {
      const tmpContract = await axios.get('/api/contracts/' + this.idContract)
      console.log(tmpContract)
      if (tmpContract !== undefined) {
        this.contract.price = tmpContract.data.price
        this.contract.electricalConsumption = tmpContract.data.electricalConsumption
        this.contract.date = tmpContract.data.date
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
        const contractToSend = {
          price: this.contractPlaceholder.price,
          date: this.contractPlaceholder.date,
          electricalConsumption: this.contractPlaceholder.electricalConsumption,
          user_idUser: {
            idUser: this.idUser
          }
        }
        // We close the modifications
        if (this.isNewContract) {
          axios.post('/api/contracts/', contractToSend)
            .then((result) => {
              console.log(result)
            }).catch((err) => {
              console.log(err)
            })
        } else {
          axios.put('/api/contracts/' + this.idContract, contractToSend)
            .then((response) => {
              console.log(response)
            })
            .catch((err) => {
              console.log(err)
            })
        }
      }
      this.isModifying = false
    }
  },

  head () {
    return { title: (this.isNewContract) ? 'new contract' : this.contract.idContract }
  }
}
</script>
