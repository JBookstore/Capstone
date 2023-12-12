<template>
    <div class="postCard">
        <h2>{{ post.title }}</h2>
        <p>{{ post.user_id }}</p>
        <p>{{ post.post_img }}</p>
        <div>
            {{ post.post_description }}
        </div>
    </div>
</template>

<script>
import plantService from '../services/PlantService';

export default {
    props: {
        post: {type: Object, required: true},
    },
    // created() {
    //     plantService.getPlantById(this.post.plant_id)
    //        .then( response => {
    //        this.$store.commit('SET_ACTIVE_PLANT', response.data)
    //     });
    // }
}
</script>