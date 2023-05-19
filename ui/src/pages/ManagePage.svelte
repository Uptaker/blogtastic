<script lang="ts">
    import MainPageLayout from 'src/layout/MainPageLayout.svelte'
    import SortableTable from "src/components/SortableTable.svelte";
    import type {Post} from "src/api/types";
    import api from "src/api/api";

    let posts: Post[]

    async function load() {
        posts = await api.get('posts')
    }

    $: load()
</script>

<MainPageLayout title="Manage posts">
    <SortableTable items={posts} columns={['title', 'Creation Date']} let:item={p} class="w-full">
        <tr>
            <td>{p.title}</td>
            <td>{new Date(p.createdAt).toLocaleString()}</td>
        </tr>
    </SortableTable>
</MainPageLayout>
