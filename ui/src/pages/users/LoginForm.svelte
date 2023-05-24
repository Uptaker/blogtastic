<script lang="ts">
    import {createEventDispatcher} from 'svelte'
    import type {User} from 'src/api/types'
    import Form from 'src/forms/Form.svelte'
    import Button from 'src/components/Button.svelte'
    import api from 'src/api/api'
    import {showToast} from 'src/stores/toasts'
    import FormField from 'src/forms/FormField.svelte'
    import {initSession} from "src/stores/auth";

    export let login = {} as {email: string, password: string}

  const dispatch = createEventDispatcher<{saved: User}>()

  async function submit() {
    const user = await api.post<User>('user/login', login)
    if (user) initSession(user)
    showToast('Welcome back, ' + user.firstName)
    dispatch('saved', user)
  }
</script>

<Form {submit}>
  <div class="common-grid sm:grid-cols-2">
    <FormField label="Email" type="email" bind:value={login.email}/>
    <FormField label="Password" type="password" bind:value={login.password}/>
  </div>

  <div class="flex justify-end">
    <Button type="submit" label="Login" class="primary"/>
  </div>
</Form>
