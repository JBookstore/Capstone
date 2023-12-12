<template>

    <div class="forumBar">
    <button id="makePostButton" v-on:click="toggleShowForm">SAY SOMETHING</button>

        <div class="postForm" v-if="showForm">
            <input type="text" />
            <input type="text" />
            <input type="text" />
            <input type="text" />
        </div>

    <hr>
    </div>
    
    <div class="preview" v-for="post in posts" v-bind:key="post.post_id">
        <forum-card v-bind:post="post"/>
    </div>

</template>

<script>
import forumCard from '../components/ForumCard.vue';
import forumService from '../services/ForumService';

export default {

    data() {
        return {
            posts: [],
            showForm: false
        }
    },

    components: {
        forumCard
    },
    
    created() {
        forumService.getPostByForums(this.$route.params.id)
        .then (response => {
            this.posts = response.data;
        })
    },

    methods: {
        createPost() {
            alert(this.$store.state.user.username + ' says - hello!');
        },
        toggleShowForm() {
            this.showForm = !this.showForm;
        }
    }
}
</script>

<style scoped>

.forumBar {
    margin-top: 10px;

    width: 80vw;
    text-align: center;
}

#makePostButton {
    margin-bottom: 10px;
}

</style>