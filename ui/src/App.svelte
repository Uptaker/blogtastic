<script lang="ts">
  import {Route, Router} from 'svelte-navigator'
  import Toasts from './components/Toasts.svelte'
  import NotFoundPage from './layout/NotFoundPage.svelte'
  import HomePage from 'src/pages/HomePage.svelte'
  import Spinner from 'src/components/Spinner.svelte'
</script>

<svelte:head>
  <title>Blog of Markus</title>
</svelte:head>

<Toasts/>

<Router primary={false}>
  <div class="App min-h-screen flex flex-col">
    <Route path="/" component={HomePage}/>
    <Route component={NotFoundPage}/>
  </div>
</Router>
