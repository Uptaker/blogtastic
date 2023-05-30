<script lang="ts">
  import {Route, Router} from 'svelte-navigator'
  import Toasts from './components/Toasts.svelte'
  import NotFoundPage from './layout/NotFoundPage.svelte'
  import HomePage from 'src/pages/HomePage.svelte'
  import ManagePage from "src/pages/ManagePage.svelte";
  import LoginPage from "src/pages/LoginPage.svelte";
  import PostPage from "src/pages/PostPage.svelte";
  import TagsPage from "src/pages/users/TagsPage.svelte";
</script>

<svelte:head>
  <title>Blog of Markus</title>
</svelte:head>

<Toasts/>

<Router primary={false}>
  <div class="App min-h-screen flex flex-col">
    <Route path="/" component={HomePage}/>
    <Route path="/post/:year/:month/:day/:slug" component={PostPage}/>
    <Route path="/manage" component={ManagePage}/>
    <Route path="/tags" component={TagsPage}/>
    <Route path="/login" component={LoginPage}/>
    <Route component={NotFoundPage}/>
  </div>
</Router>
