<script lang="ts">
   import MainPageLayout from 'src/layout/MainPageLayout.svelte'
   import Card from "src/components/Card.svelte";
   import type {User} from "src/api/types";
   import UserForm from "src/samples/UserForm.svelte";
   import LoginForm from "src/pages/users/LoginForm.svelte";
   import {navigate} from "svelte-navigator";
   import Button from "src/components/Button.svelte";

   let user: User

    let showLogin = true

    function handleLogin(e: CustomEvent) {
       const user = e.detail
       if (user.role === 'ADMIN') navigate('/manage')
       else navigate('/')
    }
</script>

<MainPageLayout title={showLogin ? 'Login' : 'Register'}>
   <div slot="header">
      <Button class="link" on:click={() => showLogin = !showLogin}>
         {showLogin ? 'New? Register instead' : 'Already have an account? Login instead'}
      </Button>
   </div>
   <Card>
      {#if showLogin}
         <LoginForm on:saved={handleLogin}/>
      {:else}
         <UserForm on:saved={handleLogin}/>
      {/if}
   </Card>
</MainPageLayout>
