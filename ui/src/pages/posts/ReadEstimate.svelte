<script lang="ts">
    import type {Post} from "src/api/types";

    export let post: Post

    $: estimate = readEstimate(post)

    function readEstimate(p: Post): number {
        const content = p.content + ' ' + p.subheadline
        const count = content.match(/\w+/g).length;
        return Math.ceil(count / 250)
    }
</script>

{#if estimate}
    <div class="{$$props.class ?? ''}">{estimate} minutes</div>
{/if}