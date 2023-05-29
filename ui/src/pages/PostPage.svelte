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
            <div class="flex justify-around items-center py-12 px-4 text-center ">
                    <img src="/api/images/{post.details.id}" alt="post main picture" style="width: 300px; height: 300px;"/>
                <div class="text-3xl">{post.details.title}</div>
            </div>
            <div class="xl:w-8/12 mx-auto flex flex-col gap-12 pt-12">
                <div class="flex justify-center">{post.author.name}</div>
                <div class="flex justify-between">
                    <div>{new Date(post.details.createdAt).toDateString()}</div>
                    <ReadEstimate post={post.details}/>
                </div>
                <div class="text-primary-800">{post.details.subheadline}</div>
                <Card class="mb-5">
                    <div class="markdown">{@html marked.parse(post.details.content)}</div>
                </Card>
            </div>

        {/if}
</MainPageLayout>

<style>
    .img-mask {
        mask-image: linear-gradient(to bottom, rgba(0, 0, 0, 1.0) 50%, transparent 100%);
    }
</style>