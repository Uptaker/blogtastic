<script lang="ts">
    import MainPageLayout from 'src/layout/MainPageLayout.svelte'
    import SortableTable from "src/components/SortableTable.svelte";
    import type {Post} from "src/api/types";
    import api from "src/api/api";
    import Card from "src/components/Card.svelte";

    let posts: Post[]

    async function load() {
        posts = await api.get('posts')
    }

    $: load()
</script>

<MainPageLayout title="Manage posts">
    <div class="grid grid-template gap-1">
        <Card padding="p-1">
            <SortableTable items={posts} columns={['title', 'Creation Date']} let:item={p} class="w-full">
                <tr class="text-sm">
                    <td>{p.title}</td>
                    <td>{new Date(p.createdAt).toLocaleString()}</td>
                </tr>
            </SortableTable>
        </Card>
        <Card>
            Select a post to start editing
        </Card>
    </div>
</MainPageLayout>

<style>
    .grid-template {
        grid-template-columns: 1fr 4fr;
    }
</style>
