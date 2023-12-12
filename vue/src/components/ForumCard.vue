<template>
        <div class="forumCard">
        <div class="cardTop">

            <h2>{{post.title}}</h2>
            <h3>${{ post.price }}</h3>
            <p>{{ post.post_description }}</p>
            <!-- <h2 v-if="length < 200">{{ post.post_description }} </h2>
            <h2 v-else> {{ preview }}</h2> -->

            <img class="cardImage" src="../assets/garden.jpg" />

            <div class="buttons">
                <button v-on:click="toPost" class="cardButton">VIEW DETAILS</button>
                <button v-on:click="reply" class="cardButton">MESSAGE</button>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    data() {
        return {
        //    length: this.post.post_description.length,
        //    preview: this.post.post_description.substring(0, 200)
        }
    },
    props: {
        post: {type: Object, required: true},
        plant: {}
    },
    methods: {
        pushToMessages() {

        },
        pushToDetails() {
            this.$router.push({name: 'post'})
        }
    }
}
</script>

<style scoped>
.forumCard {
  text-align: left;
  border: 2px solid black;
  background-color: rgb(245, 237, 237);
  border-radius: 10px;
  width: 70vw;
  height: auto;
  margin: 20px;
  padding: 10px;
}

.cardImage {
  margin-top: 5px;
  width: 70vw;
  height: auto;
}

.cardTop {
    height: auto;
}

.cardTop > h1 {
    font-size: 70%;
    color: darkgreen;
}
.cardButton {
    width: 40vw;
}

.buttons {
    display: flex;
}

</style>