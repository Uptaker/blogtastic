<script lang="ts">
  import Container from 'src/layout/Container.svelte'
  import Header from 'src/layout/Header.svelte'

  export let title = ''
  export let width = 'normal'
</script>

<svelte:head>
  {#if title}
    <title>{title}</title>
  {/if}
</svelte:head>

<div class="min-h-screen relative bg-gradient-to-tl from-orange-100 via-orange-200 to-orange-100">
  <div class="absolute inset-0 flex items-center justify-center -z-10"></div>

  <Container {width}>
    <Header/>

    {#if title}
      <div class="mb-6 flex justify-between items-center">
        <h1 class="text-2xl font-extrabold flex gap-6">{title}</h1>
        <slot name="header"/>
      </div>
    {/if}

    <slot/>
  </Container>
</div>
