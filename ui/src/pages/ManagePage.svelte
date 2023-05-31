<script lang="ts">
    import MainPageLayout from 'src/layout/MainPageLayout.svelte'
    import SortableTable from "src/components/SortableTable.svelte";
    import type {Post, Tag} from "src/api/types";
    import api from "src/api/api";
    import Card from "src/components/Card.svelte";
    import PostEditor from "src/pages/posts/PostEditor.svelte";
    import Button from "src/components/Button.svelte";
    import Icon from "src/icons/Icon.svelte";

    let posts: Post[]
    let allTags: Tag[]

    let selected: Post|undefined

    async function load() {
        posts = await api.get('posts')
        allTags = await api.get('tags')
    }

    function createPost() {
        selected = {} as Post
    }


    $: load()
</script>

<MainPageLayout title="Manage posts">
    <div slot="header">
        {#if selected}
            <Button icon="pencil-off" label="Cancel" on:click={() => selected = undefined} />
        {/if}
    </div>
    <div class="grid grid-template gap-1 overflow-y-auto">
            <SortableTable items={posts} columns={['title', 'Creation Date']} sortFields={[p => p.details.title, p => p.details.createdAt]} let:item={p} class="w-full rounded-full">
                <tr class="text-sm hover:cursor-pointer hover:bg-primary-50" on:click={() => selected = p.details}
                    class:bg-primary-100={selected?.id === p.details.id}>
                    <td>{p.details.title}</td>
                    <td class="text-xs">
                        <div title="Creation date">
                            {new Date(p.details.createdAt).toLocaleString()}
                        </div>
                        {#if p.details.updatedAt}
                            <div class="text-muted mt-1" title="Last updated">
                                {new Date(p.details.updatedAt).toLocaleString()}
                            </div>
                        {/if}
                    </td>
                </tr>
            </SortableTable>
        <Card rounding="">
            {#if selected}
                <PostEditor post={selected} on:saved={load} {allTags}/>
            {:else}
                <div class="spaced h-max flex flex-col justify-between items-center text-center align-middle">
                    <div class="text-2xl font-bold text-secondary-400">
                        <Icon name="smile" size="xxl"/>
                         <span class="text-primary-700">+</span>
                        <Icon name="notes" size="xxl"/>
                        <span class="text-primary-700">=</span>
                        <Icon name="heart" size="xxl"/>
                    </div>
                    <div>Select an existing post to make adjustments</div>
                    <div class="italic text-secondary-400 text-sm">⎯⎯  or  ⎯⎯</div>
                    <div>If you have an idea or in the right mood...</div>
                    <Button class="btn primary w-fit" icon="pencil-plus" label="Write a new Blog post" on:click={createPost}/>
                </div>
            {/if}
        </Card>
    </div>
</MainPageLayout>

<style>
    .grid-template {
        grid-template-columns: 2fr 5fr;
    }
</style>
