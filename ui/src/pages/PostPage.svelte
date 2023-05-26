<script lang="ts">
    import MainPageLayout from 'src/layout/MainPageLayout.svelte'
    import type {Post} from "src/api/types";
    import api from "src/api/api";
    import {marked} from "marked";

    export let slug: string
    let post: Post

    async function load(slug: string) {
        post = await api.get('posts/' + slug)
    }

    $: load(slug)
</script>

<MainPageLayout>
        {#if post}
            <div class="h-12 flex justify-center items-center py-24 bg-gradient-to-r from-orange-300 to-rose-300">
                <div class="text-3xl">{post.title}</div>
            </div>
            <div class="w-8/12 mx-auto">
                <div class="my-4">{post.subheadline}</div>
                <div>{@html marked.parse(post.content)}</div>
            </div>

        {/if}
</MainPageLayout>
