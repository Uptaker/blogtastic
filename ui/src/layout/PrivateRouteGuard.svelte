<script lang="ts">
  import {user} from 'src/stores/auth'
  import {navigate} from "svelte-navigator";

  $: if (!$user) navigate('/login', {state: {from: location.pathname}, replace: true})
</script>

{#if $user}
  <slot/>
{/if}
