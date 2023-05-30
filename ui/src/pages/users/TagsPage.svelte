<script lang="ts">
    import MainPageLayout from 'src/layout/MainPageLayout.svelte'
    import SortableTable from "src/components/SortableTable.svelte";
    import type {Tag} from "src/api/types";
    import api from "src/api/api";
    import Button from "src/components/Button.svelte";
    import Modal from "src/components/Modal.svelte";
    import FormField from "src/forms/FormField.svelte";
    import Form from "src/forms/Form.svelte";
    import {showToast} from "src/stores/toasts";

    let tags: Tag[]

    let selected: Tag|undefined = undefined

    async function load() {
        tags = await api.get('tags')
    }

    function createTag() {
        selected = {} as Tag
    }

    async function submit() {
        await api.post('tags', selected)
        showToast(selected?.id + ' saved.')
        selected = undefined
        await load()
    }

    async function remove(tagId: string) {
        await api.delete('tags/' + tagId)
        tags = tags.filter(t => t.id !== tagId)
    }

    $: load()
</script>

<MainPageLayout title="Manage tags">
    <div slot="header">
        <Button icon="pencil-plus" label="Add" on:click={createTag}/>
    </div>
    <SortableTable items={tags} columns={['title', '']}
                   sortFields={[p => p.id, '']} let:item={p}
                   class="w-full rounded-full">
        <tr class="text-sm hover:bg-primary-50">
            <td class="font-bold" style="color: {p.color}">{p.id}</td>
            <td>
                <Button label="Delete" class="danger" on:click={() => remove(p.id)}/>
            </td>
        </tr>
    </SortableTable>
</MainPageLayout>

<Modal title="Add new title" show={!!selected}>
    <Form {submit}>
        <div class="grid grid-cols-2 spaced gap-3">
            <FormField bind:value={selected.id} label="Label name"/>
            <FormField type="color" class="h-11" bind:value={selected.color} label="Color"/>
            <div>
                {#if selected.id && selected.color}
                    <span class="font-bold" style="color: {selected.color}">{selected.id.toUpperCase()}</span>
                {:else}
                    <span class="text-muted">Preview</span>
                {/if}
            </div>
            <div>
                <Button label="Save" type="submit" class="float-right primary"/>
            </div>
        </div>
    </Form>
</Modal>