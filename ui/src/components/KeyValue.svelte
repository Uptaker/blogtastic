<script lang="ts">
  export let label: string
  export let value: string|number|undefined = undefined
  export let valueClass = ''
</script>

{#if value || $$slots.default}
  <div class={$$props.class}>
    <p class="text-muted text-sm">{label}</p>
    <p class="text-gray-900 font-medium {valueClass}">
      {#if value}{value}{:else}<slot/>{/if}
    </p>
  </div>
{/if}
