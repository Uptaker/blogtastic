<script lang="ts">
  import MainPageLayout from 'src/layout/MainPageLayout.svelte'
  import Card from "src/components/Card.svelte";
</script>

<MainPageLayout title="Home Page">
  <div class="flex flex-col-reverse sm:flex-row md:gap-28 lg:gap-40 gap-10">
    <div class="flex flex-col gap-10 w-full">
      <Card>
        <h3>Title</h3>
        <p>Some text here</p>
      </Card>
      <Card>
        <h3>Title</h3>
        <p>Some text here</p>
      </Card>
      <Card>
        <h3>Title</h3>
        <p>Some text here</p>
      </Card>
      <Card>
        <h3>Title</h3>
        <p>Some text here</p>
      </Card>
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
