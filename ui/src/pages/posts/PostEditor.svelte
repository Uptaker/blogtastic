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
    $: date = new Date(post.date)

    const dispatch = createEventDispatcher()

    async function submit() {
        if (!post.slug) post.slug = slug
        const message = post.id ? 'Post updated' : 'Post published!'
        post = await api.post('posts', post)
        showToast(message)
        dispatch('saved', post)
    }

    $: slug = post.slug ? post.slug : post.title?.toLowerCase().replace(/[\W_]+/g," ").trim().replaceAll(' ', '-')
</script>

<Form {submit}>
    <div class="flex gap-4 justify-between">
        <Button icon={post.id ? 'edit' : 'send'} class="btn primary" type="submit" label={post.id ? 'Update' : 'Publish'}/>
        {#if slug}
            {@const pathPos = location.href.lastIndexOf('/manage')}
            {@const year = date.getFullYear()}
            {@const month = date.getMonth() + 1}
            {@const day = date.getDate()}
            <div class="text-sm">
                Article will be published on:
                <div class="text-xs text-primary-700">
                    {location.href.slice(0, pathPos)}/{year}/{month}/{day}/{slug}
                </div>

            </div>
        {/if}
    </div>
    <div class="grid grid-cols-2 p text-center text-xs text-primary-900">
        <div class="tab !border-r-0">EDIT</div>
        <div class="tab">PREVIEW</div>
    </div>
    <FormField bind:value={post.title} minlength={5} label="Headline"/>
    <TextAreaField bind:value={post.subheadline} rows={3} label="Subheadline"/>
    <TextAreaField bind:value={post.content} rows={20} label="Content" maxlength={20000}/>
</Form>

<style>
    .border-1 {
        border: 1px solid;
    }

    .tab {
        cursor: pointer;
        @apply !border-primary-100 border-1 p-2 bg-primary-50 hover:bg-primary-100
    }
</style>