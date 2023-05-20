<script lang="ts">
    import MainPageLayout from 'src/layout/MainPageLayout.svelte'
    import SortableTable from "src/components/SortableTable.svelte";
    import type {Post} from "src/api/types";
    import api from "src/api/api";
    import Card from "src/components/Card.svelte";
    import PostEditor from "src/pages/posts/PostEditor.svelte";
    import Button from "src/components/Button.svelte";
    import Icon from "src/icons/Icon.svelte";

    let posts: Post[]

    let selected: Post|undefined

    async function load() {
        posts = await api.get('posts')
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
            <SortableTable items={posts} columns={['title', 'Creation Date']} sortFields={[p => p.title, p => p.createdAt]} let:item={p} class="w-full rounded-full">
                <tr class="text-sm hover:cursor-pointer hover:bg-primary-50" on:click={() => selected = p}
                    class:bg-primary-100={selected?.id === p.id}>
                    <td>{p.title}</td>
                    <td class="text-xs">
                        <div title="Creation date">
                            {new Date(p.createdAt).toLocaleString()}
                        </div>
                        {#if p.updatedAt}
                            <div class="text-muted mt-1" title="Last updated">
                                {new Date(p.updatedAt).toLocaleString()}
                            </div>
                        {/if}
                    </td>
                </tr>
            </SortableTable>
        <Card rounding="">
            {#if selected}
                <PostEditor post={selected} on:saved={load}/>
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
