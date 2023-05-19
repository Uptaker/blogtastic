<script lang="ts">
    import MainPageLayout from 'src/layout/MainPageLayout.svelte'
    import Card from "src/components/Card.svelte";
    import type {Post} from "src/api/types";
    import api from "src/api/api";

    let posts: Post[]

    async function load() {
        posts = await api.get('posts')
    }

    $: load()
</script>

<MainPageLayout>
    <div class="flex flex-col-reverse sm:flex-row md:gap-28 lg:gap-40 gap-10">
        <div class="flex flex-col gap-10 w-full">
            {#if posts?.length}
                {#each posts as p}
                    <Card>
                        <h3>{p.title}</h3>
                        <p>{p.content}</p>
                    </Card>
                {/each}
            {/if}
        </div>
        <div class="flex sm:flex-col gap-10 md:gap-20 w-3/12">
            <Card>
                <p>Some sidebar content</p>
                <p>over here</p>
            </Card>
            <Card>
                <p>Some sidebar content</p>
                <p>over here</p>
            </Card>
        </div>
    </div>
</MainPageLayout>
