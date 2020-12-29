<template>
  <v-container>
    <!-- when loading : progress-bar -->
    <CustomProgressBar v-if="isLoading" message="Loading the user's data..." />

    <div v-else>
      <!-- Buttons for modifications -->
      <ButtonsChanges
        :is-modifying="isModifying"
        text-activate="Modify the User"
        :activate="openChanges"
        :discard="discardChanges"
        :validate="saveChanges"
      />

      <v-card shaped>
        <!-- User's name -->
        <h1 class="text-center">
          {{ isNewUser ? 'New user' : user.nameFirst + ' ' + user.nameLast }}
        </h1>

        <!-- Divider -->
        <v-divider class="ma-8" />

        <!-- Form for the user's data -->
        <v-form ref="form" v-model="form">
          <v-container>
            <v-row justify="center">
              <!-- User's civility -->
              <v-col cols="12" sm="4">
                <v-select
                  v-model="userPlaceholder.civility"
                  :items="['Mr.', 'Ms.', 'Other']"
                  :disabled="!isModifying"
                  single-line
                  prepend-icon="mdi-gender-male-female"
                />
              </v-col>

              <!-- User's first name -->
              <v-col cols="12" sm="4">
                <v-text-field
                  v-model="userPlaceholder.nameFirst"
                  label="First name"
                  :rules="[rules.required, rules.maxSmall]"
                  :disabled="!isModifying"
                  clearable
                  class="ma-4"
                  prepend-icon="mdi-human-handsup"
                  type="text"
                />
              </v-col>

              <!-- User's last name -->
              <v-col cols="12" sm="4">
                <v-text-field
                  v-model="userPlaceholder.nameLast"
                  label="Last name"
                  :rules="[rules.required, rules.maxSmall]"
                  :disabled="!isModifying"
                  clearable
                  class="ma-4"
                  prepend-icon="mdi-tournament"
                  type="text"
                />
              </v-col>

              <!-- User's address -->
              <v-col cols="12" sm="4">
                <v-text-field
                  v-model="userPlaceholder.address"
                  label="address"
                  :rules="[rules.required, rules.maxSmall]"
                  :disabled="!isModifying"
                  clearable
                  class="ma-4"
                  prepend-icon="mdi-map-marker"
                  type="text"
                />
              </v-col>

              <!-- User's zip -->
              <v-col cols="12" sm="4">
                <v-text-field
                  v-model="userPlaceholder.zipCode"
                  label="ZIP Code"
                  :rules="[rules.required]"
                  :disabled="!isModifying"
                  clearable
                  class="ma-4"
                  prepend-icon="mdi-numeric"
                  type="number"
                />
              </v-col>

              <!-- User's city -->
              <v-col cols="12" sm="4">
                <v-text-field
                  v-model="userPlaceholder.city"
                  label="City"
                  :rules="[rules.required, rules.maxSmall]"
                  :disabled="!isModifying"
                  clearable
                  class="ma-4"
                  prepend-icon="mdi-city"
                  type="text"
                />
              </v-col>

              <!-- User's date of admission -->
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
                      v-model="userPlaceholder.dateCreation"
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
                    v-model="userPlaceholder.dateCreation"
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
const lodash = require('lodash')

export default {
  name: 'PageUserEdit',

  components: {
    ButtonsChanges,
    CustomProgressBar
  },

  mixins: [MixinRules],

  data: () => ({
    // Whether the page is loading data
    isLoading: true,

    // Whether the user is modifiying data
    isModifying: false,

    // Whether the form is valid or not
    form: false,
    menuDate: false,

    // The user's data and the placeholder
    user: {
      idUser: -1,
      civility: '',
      nameFirst: '',
      nameLast: '',
      address: '',
      zipCode: 0,
      city: '',
      dateCreation: null
    },
    userPlaceholder: {
      idUser: -1,
      civility: '',
      nameFirst: '',
      nameLast: '',
      address: '',
      zipCode: 0,
      city: '',
      dateCreation: null
    }
  }),

  computed: {
    /** Return whether this page is for a new User or not */
    isNewUser () {
      return this.$route.params.idUser === undefined
    },

    /** Return the id of the User, if he has one */
    idUser () {
      return (this.isUser) ? undefined : parseInt(this.$route.params.idUser)
    }
  },

  watch: {
    menuDate (val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
    }
  },

  mounted () {
    // Blablabla, we set the user with HARD CODED VALUES
    this.user = {
      idUser: this.idUser,
      civility: 'Mr.',
      nameFirst: 'John',
      nameLast: 'DOE',
      address: '30-32, Avenue de la République',
      zipCode: 94800,
      city: 'Villejuif',
      dateCreation: null
    }

    // We set the placeholder
    this.userPlaceholder = lodash.cloneDeep(this.user)

    // We consider the loading done
    this.isLoading = false
  },

  methods: {
    /** Required method to save the date */
    save (date) {
      this.$refs.menuDate.save(date)
    },
    /** Opens all changes brought to the user */
    openChanges () {
      // We open the modifications
      this.isModifying = true
    },

    /** Discards all changes brought to the user */
    discardChanges () {
      // We reset the placeholder
      this.userPlaceholder = lodash.cloneDeep(this.user)

      // We close the modifications
      this.isModifying = false
    },

    /** Saves all changes brought to the user */
    saveChanges () {
      // If the form is valid
      if (this.$refs.form.validate()) {
        // We close the modifications
        this.isModifying = false
      }
    }
  },

  head () {
    return { title: (this.isNewUser) ? 'new user' : this.user.nameFirst + ' ' + this.user.nameLast }
  }
}
</script>
