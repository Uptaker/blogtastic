<script lang="ts">
  import {logout, user} from 'src/stores/auth'
  import Logo from 'src/layout/Logo.svelte'
  import Button from 'src/components/Button.svelte'
  import Link from 'src/components/Link.svelte'
  import {Role} from 'src/api/types'
  import Badge from 'src/components/Badge.svelte'

  interface Menu {path: string, roles: any[], label: string}
  const menu: Menu[] = [
    {path: '/', label: 'Home', roles: []},
    {path: '/manage', label: 'Manage', roles: [Role.ADMIN]},
  ]

  function isActive(menu: Menu) {
    const path = location.pathname
    if (menu.path === path) return true
    if (path.startsWith(menu.path)) return
    return false
  }
</script>

<header class="py-4 sm:py-8 flex justify-between items-center">
  <Link to="/">
    <Logo/>
  </Link>
  <div class="flex sm:gap-3 items-center">
    {#each menu as m}
      <Link to={m.path} label={m.label} class="btn link sm {isActive(m) ? 'bg-primary-50' : ''}"/>
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
