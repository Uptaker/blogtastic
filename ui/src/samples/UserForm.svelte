<script lang="ts">
    import {createEventDispatcher} from 'svelte'
    import type {User} from 'src/api/types'
    import Form from 'src/forms/Form.svelte'
    import Button from 'src/components/Button.svelte'
    import api from 'src/api/api'
    import {showToast} from 'src/stores/toasts'
    import FormField from 'src/forms/FormField.svelte'
    import {initSession} from "src/stores/auth";

    export let user: User = {} as User
  export let savePath = 'user/register'

  const dispatch = createEventDispatcher<{saved: User}>()

  async function submit() {
    user = await api.post<User>(savePath, user)
    if (user) initSession(user)
    showToast('Welcome, ' + user.firstName)
    dispatch('saved', user)
  }
</script>

<Form {submit}>
  <div class="common-grid sm:grid-cols-2">
    <FormField label="Firstname" bind:value={user.firstName} autofocus={!!user.id}/>
    <FormField label="Lastname" bind:value={user.lastName}/>
    <FormField label="Email" type="email" bind:value={user.email}/>
    <FormField label="Password" type="password" bind:value={user.password}/>
  </div>

  <div class="flex justify-end">
    <Button type="submit" label="general.save" class="primary"/>
  </div>
</Form>
