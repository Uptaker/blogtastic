<script lang="ts">
  import {logout, user} from 'src/stores/auth'
  import Logo from 'src/layout/Logo.svelte'
  import Button from 'src/components/Button.svelte'
  import Link from 'src/components/Link.svelte'
  import {Role} from 'src/api/types'
  import Badge from 'src/components/Badge.svelte'

  const menu = [
    {path: '/', label: 'Home', roles: []},
    {path: '/manage', label: 'Manage', roles: [Role.ADMIN]},
  ]
</script>

<header class="py-4 sm:py-8 flex justify-between items-center">
  <Logo/>
  <div class="flex sm:gap-3 items-center">
    {#each menu as m}
      <Link to={m.path} label={m.label} class="btn link sm {location.pathname.substring(1).startsWith(m.path) ? 'bg-primary-50' : ''}"/>
    {/each}
    {#if $user}
      <Badge>
        {$user.firstName} {$user.lastName}
        <Button icon="x" class="sm -mr-2" on:click={logout} title="Logout"/>
      </Badge>
    {:else}
      Login
    {/if}
  </div>
</header>
