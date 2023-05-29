<script lang="ts">
    export let content: string

    $: estimate = readEstimate(content)

    function readEstimate(content: string): number {
        const count =  content.match(/\w+/g).length;
        return Math.ceil(count / 250)
    }
</script>

{#if estimate}
    <div class="{$$props.class ?? ''}">{estimate} minutes</div>
{/if}