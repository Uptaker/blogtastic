<script lang="ts">
    import type {Post, Tag} from "src/api/types";
    import Form from "src/forms/Form.svelte";
    import api from "src/api/api";
    import {showToast} from "src/stores/toasts";
    import FormField from "src/forms/FormField.svelte";
    import TextAreaField from "src/forms/TextAreaField.svelte";
    import Button from "src/components/Button.svelte";
    import {createEventDispatcher} from "svelte";
    import {user} from "src/stores/auth";
    import {marked} from "marked";
    import MultipleSelect from "src/forms/MultipleSelect.svelte";

    export let post: Post
    export let allTags: Tag[]

    $: load(post.id)

    async function load(postId: string) {
        tags = postId ? (await api.get(`posts/${postId}/tags`)).map(t => t.tagId) : []
    }

    let tags: string[]

    let image: FileList
    let showPreview = false
    $: date = new Date(post.date || Date.now())

    const dispatch = createEventDispatcher()

    async function submit() {
        if (!post.slug) post.slug = slug
        if (!post.userId) post.userId = $user.id
        post = await api.post('posts', {post, tags})
        if (image) await saveImage()
        const message = post.id ? 'Post updated' : 'Post published!'
        showToast(message)
        dispatch('saved', post)
    }

    async function saveImage() {
        const formData = new FormData()
        formData.append('image', image[0]);
        for (const file of image) {
            console.log(`${file.name}: ${file.size} bytes`);
        }
        await api.post('images/post/' + post.id, formData, undefined, false)
    }

    $: slug = post.slug ? post.slug : post.title?.toLowerCase().slice(0, 40).replace(/[\W_]+/g," ").trim().replaceAll(' ', '-')
</script>

<Form {submit}>
    <div class="flex gap-4 justify-between">
        <Button icon={post.id ? 'edit' : 'send'} class="btn primary h-min" type="submit" label={post.id ? 'Update' : 'Publish'}/>
        <div>
            <MultipleSelect label="Tags" options={allTags.indexBy(t => t.id, t => t.id)} bind:values={tags}/>
        </div>
    </div>

    <div class="grid grid-cols-2 p text-center text-xs text-primary-900 uppercase">
        <div class="tab !border-r-0" on:click={() => showPreview = false} class:!bg-primary-200={!showPreview}>EDIT</div>
        <div class="tab" on:click={() => showPreview = true} class:!bg-primary-200={showPreview}>PREVIEW</div>
    </div>
    {#if slug}
        {@const pathPos = location.href.lastIndexOf('/manage')}
        <div class="text-sm">
            Article will be published on:
            <div class="text-xs text-primary-700">
                {location.href.slice(0, pathPos)}/{date.fullDate()}/{slug}
            </div>
        </div>
    {/if}
    {#if showPreview}
        <div class="markdown">{@html marked.parse(post.content)}</div>
    {:else}
        <FormField bind:value={post.title} minlength={5} label="Headline"/>
        <TextAreaField bind:value={post.subheadline} rows={3} label="Subheadline"/>
        <input type="file" bind:files={image} accept="image/png, image/jpeg" required={!post.id}>
        <TextAreaField bind:value={post.content} rows={40} label="Content" maxlength={20000}/>
    {/if}
</Form>

<style>
    .tab {
        cursor: pointer;
        border: 1px solid;
        @apply border-primary-100 p-2 bg-primary-50 hover:bg-primary-100
    }
</style>