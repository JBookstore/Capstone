<template>
    <div class="card">
        <div class="cardTop">
            <h2>{{ plant.common_name }}</h2>
            <button v-on:click="onAddClick" class="cardButton">+</button>
            <button v-on:click="onDeleteClick" class="cardButton">-</button>
            <button v-on:click="onDetailsClick" class="cardButton">DETAILS</button>
        </div>
        <img v-if="plant.plant_img != null" v-bind:src="plant.plant_img" class="cardImage" v-on:click="onDetailsClick">
        <img v-else-if="plant.regular_img_url != null" v-bind:src="plant.regular_img_url"  class="cardImage" v-on:click="onDetailsClick"/>
        <img v-else v-bind:src="getDefaultImage" class="cardImage" v-on:click="onDetailsClick">
        <input type="file" ref="fileInput" @change="uploadImage">
    </div>
</template>

<script>
    import plantService from '../services/PlantService.js';

    export default {
        data() {
            return {
                customImage: '',
                userPlant: {}
            }
        },

        props: {
            plant: { type: Object, required: true }
        },

        methods: {
            onDetailsClick() {
                this.$router.push({ name: 'detailById', params: {id: this.plant.plant_id}});
                this.$store.commit('STORE_PLANT', this.plant);
            },
            onAddClick() {
                // We have to assign the garden Id TO the plant before we pass it
            
                this.$store.commit('MODIFY_PLANT_FOR_ADDITION', this.plant);
                this.$store.state.transferPlantJSON.garden_id = this.$store.state.user_garden.garden_id;

                plantService.addPlantToGarden(this.$store.state.transferPlantJSON);

                alert('Added one ' + this.plant.common_name + ' to your garden!');
            },
            onDeleteClick() {
            },

            convertImageToBase64(file) {
                return new Promise((resolve, reject) => {
                    const reader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = () => resolve(reader.result);
                    reader.onerror = error => reject(error);
                });
            },
            async uploadImage() {
                try {
                    const file = this.$refs.fileInput.files[0];
                    const base64 = await this.convertImageToBase64(file);
                    
                    alert(base64);
                    
                    this.userPlant.plant_img = base64;

                    await plantService.updatePlant(this.userPlant);
                } catch (error) {
                    console.error('Error uploading image:', error);
                }
            }
        },

        computed: {
            getDefaultImage() {
                return 'https://developers.google.com/static/maps/documentation/streetview/images/error-image-generic.png';
            }
        },

        created() {
            this.userPlant = this.plant;
        }
    }
</script>

<!-- We can fix image ratio by specifying width and leaving height undefined -->
<style scoped>
.card {
  text-align: center;
  border: 2px solid black;
  background-color: hotpink;
  border-radius: 10px;
  width: 80vw;
  height: auto;
  margin: 20px;
  padding: 10px;
}

.cardImage {
  margin-top: 5px;
  width: 78vw;
  height: auto;
}

.cardTop {
    height: auto;
}

.cardButton {
    width: 40vw;
}

@media screen and (min-width: 600px) {
    .card {
        width: 33vw;
        margin: 2px;
        height: auto;
    }
    .cardTop {
        height: auto;
    }
    .cardImage {
        height: auto;
        width: 28vw;
    }
    .cardButton {
        width: 15vw;
    }

}

@media screen and (min-width: 992px) {
    
    .card {
        width: 18vw;
        margin: 2px;
        height: auto;
    }
    .cardTop {
        height: auto;
    }

    .cardImage {
        height: auto;
        width: 17vw;
    }

    .cardButton {
        width: 8vw;
    }
}
</style>