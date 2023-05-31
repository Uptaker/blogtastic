<script lang="ts">
    import MainPageLayout from 'src/layout/MainPageLayout.svelte'
    import SortableTable from "src/components/SortableTable.svelte";
    import type {PostTagCount, Tag} from "src/api/types";
    import api from "src/api/api";
    import Button from "src/components/Button.svelte";
    import Modal from "src/components/Modal.svelte";
    import FormField from "src/forms/FormField.svelte";
    import Form from "src/forms/Form.svelte";
    import {showToast, ToastType} from "src/stores/toasts";

    let tags: PostTagCount[]

    let selected: Tag|undefined = undefined

    async function load() {
        tags = await api.get('tags/count')
    }

    function createTag() {
        selected = {color: '#2B2B2B'} as Tag
    }

    async function submit() {
        await api.post('tags', selected)
        showToast(selected?.id + ' saved.')
        selected = undefined
        await load()
    }

    async function remove(tagId: string) {
        await api.delete('tags/' + tagId)
        showToast(tagId + ' deleted', {type: ToastType.INFO})
        tags = tags.filter(t => t.tag.id !== tagId)
    }

    $: load()
</script>

<MainPageLayout title="Manage tags">
    <div slot="header">
        <Button icon="pencil-plus" label="Add" on:click={createTag}/>
    </div>
    <SortableTable items={tags} columns={['title', 'posts', '']} sortFields={[t => t.tag.id, t => t.count, '']} let:item={t}
                   class="w-full rounded-full">
        <tr class="text-sm hover:bg-primary-50">
            <td class="font-bold" style="color: {t.tag.color}">{t.tag.id}</td>
            <td class="font-bold">{t.count}</td>
            <td>
                <Button label="Delete" class="danger" on:click={() => remove(t.tag.id)}/>
            </td>
        </tr>
    </SortableTable>
</MainPageLayout>

<Modal title="Add new title" show={!!selected}>
    <Form {submit}>
        <div class="grid grid-cols-2 spaced gap-3">
            <FormField bind:value={selected.id} label="Label name"/>
            <div class="flex flex-col items-start align-top justify-center w-full h-min !m-0">
                <label for="color">Color</label>
                <input class="w-full !h-11" type="color" bind:value={selected.color} id="color" required/>
            </div>
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