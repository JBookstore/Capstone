<template>
<h2>In Progress</h2>
<div id="inbox" v-for="message in this.$store.state.userMessages" v-bind:key="message.message_id">
    <message-preview v-bind:message="message" />
</div>
</template>

<script>
import messageService from '../services/MessageService.js';
import messagePreview from '../components/MessagePreview.vue';
export default {

components: {
    messagePreview
},

created() {
    messageService.getUserMessages(this.$store.state.user.id).then ( response => {
      this.$store.commit('SET_USER_MESSAGES', response.data)
    })
}
}

</script>

<style scoped>
</style>