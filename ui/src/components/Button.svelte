<script lang="ts">
  import Icon from 'src/icons/Icon.svelte'

  export let icon = ''
  export let size: 'sm'|''|'lg' = 'sm'
  export let label = ''
  export let type: 'button'|'submit' = 'button'

  $: hasLabel = label || $$slots.default
</script>

<button {type} {...$$restProps} class="btn {$$props.class ?? 'default'} {size} {icon ? 'inline-flex items-center' : ''}" class:icon-only={icon && !hasLabel} on:click>
  {#if icon}
    <Icon name={icon} {size}/>
  {/if}
  {#if hasLabel}
    <span class:ml-2={icon}><slot>{label}</slot></span>
  {/if}
</button>

<style global>
  .btn {
    @apply border border-transparent rounded-md text-center inline-flex
           focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-opacity-75
           disabled:opacity-50 justify-center py-2 px-4 text-sm font-medium
  }

  .btn.default {
    @apply focus:ring-primary-500 hover:bg-gray-50 bg-white text-gray-700 border border-gray-300
  }

  .btn.primary {
    @apply text-white hover:text-white bg-primary-600 hover:bg-primary-700 focus:ring-primary-500
  }

  .btn.danger {
    @apply text-danger-500 border border-gray-300 hover:bg-gray-50
  }

  .btn.success {
    @apply text-white hover:text-white bg-success-400 hover:bg-success-700 focus:ring-success-500
  }

  .btn.secondary {
    @apply text-white hover:text-white bg-secondary-400 hover:bg-secondary-700 focus:ring-secondary-500
  }

  .btn.link {
    @apply text-primary-600 hover:text-primary-500 hover:bg-primary-100 focus:ring-primary-400
  }

  .btn.sm {
    @apply py-1.5 px-3
  }

  .btn.lg {
    @apply py-3 px-4 font-semibold
  }

  .btn.icon-only {
    @apply px-2
  }

  .btn.icon-only.sm {
    @apply px-1.5
  }
</style>
