<template>

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
            posts: []
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
    }
}
</script>