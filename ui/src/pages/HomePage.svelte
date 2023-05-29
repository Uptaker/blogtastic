<script lang="ts">
    import MainPageLayout from 'src/layout/MainPageLayout.svelte'
    import Card from "src/components/Card.svelte";
    import type {Post, PostInList} from "src/api/types";
    import api from "src/api/api";
    import {Link} from "svelte-navigator";
    import ReadEstimate from "src/pages/posts/ReadEstimate.svelte";

    let posts: PostInList[]
    let random: Post[]

    async function load() {
        posts = await api.get('posts')
        random = await api.get('posts/random')
    }

    $: load()
</script>

<MainPageLayout>
    <div class="flex flex-col-reverse sm:flex-row md:gap-28 lg:gap-40 gap-10">
        <div class="flex flex-col gap-10 w-full">
            {#if posts?.length}
                {#each posts as p}
                    <Card padding="py-2 px-3">
                        <div class="flex gap-3">
                            <img src="/api/images/{p.details.id}" alt="post main picture" style="width: 150px; height: 150px;"/>
                            <div>
                                <Link to="/post/{p.details.fullDate}/{p.details.slug}" class="text-2xl">{p.details.title}</Link>
                                <div class="!text-muted text-sm my-2">{p.details.subheadline}</div>
                                <div class="flex text-xs text-secondary-500 justify-start gap-3">
                                    <div>{new Date(p.details.createdAt).toDateString()}</div>
                                    <div>{p.author.name}</div>
                                    <ReadEstimate post={p.details}/>
                                </div>
                            </div>
                        </div>
                    </Card>
                {/each}
            {/if}
        </div>
        <div class="flex sm:flex-col gap-10 md:gap-20 w-3/12">
            <Card>
                <p class="font-bold">Random</p>
                {#if random?.length}
                    {#each random as p}
                        <p>{p.title}</p>
                    {/each}
                {/if}
            </Card>
            <Card>
                <p>Some sidebar content</p>
                <p>over here</p>
            </Card>
        </div>
    </div>
</MainPageLayout>
