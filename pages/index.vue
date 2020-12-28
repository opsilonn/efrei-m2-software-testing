<template>
  <v-container>
    <v-card>
      <!-- Title -->
      <h1 class="text-center">
        Software Testing
      </h1>

      <!-- User's title -->
      <div>
        <v-card-title>
          Adding a user
        </v-card-title>

        <!-- User's form -->
        <v-container fluid>
          <v-row align="center">
            <v-col cols="6">
              <v-select
                v-model="selectedUser"
                :items="userItems"
                item-text="name"
                return-object
                single-line
                prepend-icon="mdi-account-box"
              />
            </v-col>
            <v-col cols="6" class="text-center">
              <NuxtLink :to="selectedUser.idUser === -1 ? '/user' : `/user/${selectedUser.idUser}`">
                <v-btn
                  x-large
                  color="primary"
                  dark
                  class="zoom-sm"
                >
                  {{ selectedUser.idUser === -1 ? 'New user' : 'Modify user' }}
                </v-btn>
              </NuxtLink>
            </v-col>
          </v-row>
        </v-container>
      </div>

      <!-- Contract's title (only for a selected user) -->
      <div v-if="selectedUser.idUser !== -1">
        <!-- Divider -->
        <v-divider />

        <v-card-title>
          Adding a contract
        </v-card-title>

        <!-- Contract's form -->
        <v-container fluid>
          <v-row align="center">
            <v-col cols="6">
              <v-select
                v-model="selectedContract"
                :items="contractItems"
                item-text="name"
                return-object
                single-line
                prepend-icon="mdi-file-document-edit-outline"
              />
            </v-col>
            <v-col cols="6" class="text-center">
              <NuxtLink :to="selectedContract.idContract === -1 ? '/contract' : `/contract/${selectedContract.idContract}`">
                <v-btn
                  x-large
                  color="primary"
                  dark
                  class="zoom-sm"
                >
                  {{ selectedContract.idContract === -1 ? 'New contract' : 'Modify contract' }}
                </v-btn>
              </NuxtLink>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </v-card>
  </v-container>
</template>

<script>
// Imports

export default {
  name: 'PageIndex',

  components: {
  },

  props: {
  },

  async asyncData ({ store, params }) {
  },

  data () {
    return {
      selectedUser: { idUser: -1, name: 'New user' },
      selectedContract: { idContract: -1, name: 'New contract' }
    }
  },

  computed: {
    /** List of all the users */
    users () {
      return [{ idUser: 12345678, civility: 'M.', nameFirst: 'John', nameLast: 'DOE' }]
    },

    /** List of all the user items for the select widget */
    userItems () {
      // We declare the list
      const items = [{ idUser: -1, name: 'New user' }]

      // We add each user to the list
      this.users.forEach(_ => items.push({
        idUser: _.idUser,
        name: _.civility + ' ' + _.nameFirst + ' ' + _.nameLast
      }))

      // We return the list
      return items
    },

    /** The current contract, if any */
    contractCurrent () {
      return { idContract: 12345678 } || undefined
    },

    /** List of all the user items for the select widget */
    contractItems () {
      // We declare the list
      const items = [{ idContract: -1, name: 'New contract' }]

      // If there is a contract, we add it to the list
      if (this.contractCurrent !== undefined) {
        items.push({ idContract: this.contractCurrent.idContract, name: 'Current contract' })
      }

      // We return the list
      return items
    }
  },

  watch: {
  },

  mounted () {
  },

  methods: {
  },

  head () {
    return { title: 'Homepage' }
  }
}
</script>
