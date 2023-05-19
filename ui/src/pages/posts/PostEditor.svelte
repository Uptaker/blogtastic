<script lang="ts">
    import type {Post} from "src/api/types";
    import Form from "src/forms/Form.svelte";
    import api from "src/api/api";
    import {showToast} from "src/stores/toasts";
    import FormField from "src/forms/FormField.svelte";
    import TextAreaField from "src/forms/TextAreaField.svelte";
    import Button from "src/components/Button.svelte";
    import {createEventDispatcher} from "svelte";

    export let post: Post

    const dispatch = createEventDispatcher()

    async function submit() {
        const message = post.id ? 'Post updated' : 'Post published!'
        post = await api.post('posts', post)
        showToast(message)
        dispatch('saved', post)
    }
</script>

<Form {submit}>
    <Button icon={post.id ? 'edit' : 'send'} class="btn primary" type="submit" label={post.id ? 'Update' : 'Publish'}/>
    <FormField bind:value={post.title} minlength={5} label="Title"/>
    <TextAreaField bind:value={post.content} rows={20} label="Content"/>
</Form>