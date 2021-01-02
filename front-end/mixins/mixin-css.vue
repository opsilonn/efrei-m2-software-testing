<script>
export default {
  name: 'MixinCss'
}
</script>

<style>
/* Changes the mouse's cursor on Pointer */
.pointer {
  cursor: pointer;
}

/* Zoom effect when overing */
h1,
h2 {
  color: #147FC6;
}

/* No underline effect for NuxtLink (does not work) */
NuxtLink {
  text-decoration: none;
}

/* V-card class */
.v-card.v-sheet.theme--dark {
  background-color: #212121;
  transition: all 0.15s linear;
  margin: 1em;
  padding: 1em;
}
.v-card.v-sheet.theme--dark:hover {
  background-color: #292929;
}

/* V-card class inside another V-card class */
.v-card.v-sheet.theme--dark .v-card.v-sheet.theme--dark {
  background-color: #525252;
  transition: all 0.15s linear;
  padding: 1em;
}

/* V-card class inside another V-card class */
.v-card.v-sheet.theme--dark .v-card.v-sheet.theme--dark:hover {
  background-color: #757575;
  transform: scale(1.05);
}

/* Nuxtlink */
.v-application a {
  color: inherit !important;
  text-decoration: none;
}

/* Zoom effect when overing */
.zoom {
  transition: transform 0.2s;
}
.zoom:hover {
  transform: scale(1.2);
}
.zoom-sm {
  transition: transform 0.2s;
}
.zoom-sm:hover {
  transform: scale(1.1);
}
.zoom-xs {
  transition: transform 0.2s;
}
.zoom-xs:hover {
  transform: scale(1.05);
}
</style>
