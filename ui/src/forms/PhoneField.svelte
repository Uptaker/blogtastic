<script lang="ts">
  import FormField from './FormField.svelte'
  import {CountryCode} from 'src/api/types'

  export let label: string|undefined = undefined
  export let value: string = ''
  export let required = true

  interface $$Events {}

  async function focus(e: FormEvent) {
    if (!value) {
      setTimeout(() => e.currentTarget?.setSelectionRange(value.length, value.length))
    }
  }

</script>

<FormField type="tel" bind:value {label} on:focus={focus} minlength={10} maxlength={15} {required} {...$$restProps}/>
