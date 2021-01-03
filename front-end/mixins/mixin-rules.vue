<script>
export default {
  name: 'MixinRules',

  data: () => ({
    // Rules
    rules: {
      required: value => !!value || 'Required',
      maxSmall: value => (value !== null && value !== undefined && value.length <= 50) || 'Max 50 characters',
      maxBig: value => (value !== null && value !== undefined && value.length <= 500) || 'Max 500 characters',
      notNegative: value => (value >= 0) || 'No negative value !',
      ascii: value => (value !== null && value !== undefined && value.split('').every(v => v.charCodeAt(0) >= 32 && v.charCodeAt(0) <= 255)) || 'Contains invalid character',
      nameMatch (name) { return value => value === name || 'The names do not match !' },
      passwordMatch (name) { return value => value === name || 'The passwords do not match !' }
    }
  })
}
</script>
