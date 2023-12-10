<template>
   <div class="card" v-on:click="onClick" v-bind:class="[message.is_read ? 'read' : 'unread']">
        <div class="sender"> {{ message.from_user_id }}</div>
        <div class="preview"> {{ message.message_body }}</div>
   </div>
</template>

<script>
export default{
    props: {
        message: { type: Object, required: true}
    },
    methods: {
        onClick() {
            this.$router.push({name: 'message', params: {id: this.message.message_id}});
            this.$store.commit('SET_ACTIVE_MESSAGE', this.message)
        }
    }
}
</script>

<style scoped>
.card {
  text-align: center;
  border: 2px solid black;
  border-radius: 10px;
  width: 60vw;
  height: auto;
  margin: 20px;
  padding: 10px;
}

.card.read {
    background-color: white;
}

.card.unread {
    background-color: gray;
}

.sender {
    text-align: end;
}
</style>