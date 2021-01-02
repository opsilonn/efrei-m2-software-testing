<template>
  <v-app dark>
    <!-- App bar (navbar) -->
    <AppBar />

    <!-- Container for Nuxt's page -->
    <v-main>
      <div class="fill-height" fluid>
        <nuxt />
      </div>
    </v-main>

    <!-- Footer to display the year -->
    <v-footer app class="shrink d-none d-md-flex">
      <span> &copy; {{ new Date().getFullYear() }} - Software Testing </span>
    </v-footer>
  </v-app>
</template>

<script>
// Imports
import AppBar from '@/components/app-bar'
import MixinCss from '@/mixins/mixin-css'

export default {
  components: {
    AppBar
  },

  mixins: [MixinCss]
}
</script>
