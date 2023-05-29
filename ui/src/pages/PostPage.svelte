<script lang="ts">
    import MainPageLayout from 'src/layout/MainPageLayout.svelte'
    import type {PostInList} from "src/api/types";
    import api from "src/api/api";
    import {marked} from "marked";
    import Card from "src/components/Card.svelte";
    import ReadEstimate from "src/pages/posts/ReadEstimate.svelte";

    export let slug: string
    let post: PostInList

    async function load(slug: string) {
        post = await api.get('posts/' + slug)
    }

    $: load(slug)
</script>

<MainPageLayout>
        {#if post}
            <div class="h-12 flex justify-center items-center py-24 px-4 text-center bg-gradient-to-r from-orange-300 to-rose-300">
                <div class="text-3xl">{post.details.title}</div>
            </div>
            <div class="xl:w-8/12 mx-auto flex flex-col gap-12 pt-12">
                <div class="flex justify-center">{post.author.name}</div>
                <div class="flex justify-between">
                    <div>{new Date(post.details.createdAt).toDateString()}</div>
                    <ReadEstimate post={post.details}/>
                </div>
                <div class="text-primary-800">{post.details.subheadline}</div>
                <Card>
                    <div class="markdown">{@html marked.parse(post.details.content)}</div>
                </Card>
            </div>

        {/if}
</MainPageLayout>
