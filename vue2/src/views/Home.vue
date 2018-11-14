<template>
    <ais-index
    app-id="AV5HLF6Z2Q"
    api-key="af1ff1cbe35aac61ee4fa9622a1fd2ec"
    index-name="checklists"
  >
    <ais-input class="input is-large" placeholder="What do you need to be prepared for?" style="margin-top: 20px; margin-bottom: 50px"></ais-input>

    <ais-results>
      <template slot-scope="{ result }">
        <router-link :to="`/checklist/${result.id}`">
        <div class="card">
          <div class="card-content">
            <p class="title">
              {{ result.title }}
            </p>
            <p class="subtitle">
              {{ result.items.slice(0, 3).map(function(item) { return item.title }).join(', ') }}...
            </p>
          </div>
        </div>
        </router-link>
      </template>
    </ais-results>
  </ais-index>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'home',
  components: {
    HelloWorld
  }
}
</script>

<style>
  .card {
    background-color: #ffffffad !important;
    backdrop-filter: blur(2px);
  }
</style>

