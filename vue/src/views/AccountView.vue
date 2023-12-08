<template>
    <div>

        <!-- MY GARDENS -->
        <div class="gardenCard" v-for="garden in this.userGardens" v-bind:key="garden.garden_id">
            <h2> {{ garden.garden_name }}</h2>
        <img class="cardImage" src="../assets/garden.jpg"  v-on:click="this.$router.push({ name: 'gardenView', params: {id: garden.garden_id}});"/>
        </div>

        <div class="home">
            <p>How does your garden grow?</p>
            <button v-on:click="createGarden">UP</button>
        </div>

        <form v-on:submit.prevent="submitForm" class="cardForm" v-if="showForm">
            <hr>
            <fieldset class="mandatory">
                <label for="title">Title:</label>
                <input type="text" v-model="addedGarden.garden_name" />
            </fieldset>

            <fieldset class="optional">
                <label for="address">Address:</label>
                <input type="text" v-model="addedGarden.street_address" />
            </fieldset>
            <fieldset class="optional">
                <label for="city">City:</label>
                <input type="text" v-model="addedGarden.garden_city" />
            </fieldset>
            <fieldset class="optional">
                <label for="state">State:</label>
                <input type="text" v-model="addedGarden.garden_state" />
            </fieldset>
            <fieldset class="optional">
                <label for="zip">ZIP:</label>
                <input type="text" v-model="addedGarden.garden_zip" />
            </fieldset>
            <fieldset class="optional">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" v-model="addedGarden.phone_number" />
            </fieldset>

            <fieldset class="mandatory">
                <label for="isPublic">Public?</label>
                <input type="text" v-model="addedGarden.is_public" />
            </fieldset>
            <fieldset class="mandatory">
                <label for="gardenType">Garden Type:</label>
                <input type="text" v-model="addedGarden.garden_type" />
            </fieldset>

            <button v-on:click="clearForm">CLEAR</button>
            <button v-on:click="submitGarden">SUBMIT</button>
        </form>

        <!-- OPEN ACCOUNT -->

        <!-- MY MARKETPLACE -->

        <!-- [SET] REMINDERS -->

        <!-- [SET] EVENTS -->

        <!-- VOLUNTEER -->

    </div>
</template> 
<script>
import plantService from '../services/PlantService.js';

export default {
    data() {
        return {
            showForm: false,
            addedGarden: {
                user_id: this.$store.state.user.id
            },
            userGardens: []
        }
    },

    methods: {
        createGarden() {
            this.showForm = true;
            alert('This button works!!');

            // this.showForm = false;
        },
        clearForm() {
            // alert('Cleared');
            this.showForm = false;
            this.garden = {};
        },
        submitGarden() {
            alert('Submitted');
            plantService.addGarden(this.addedGarden);
            this.showForm = false;
            this.addedGarden = {};
        },
        getUserGardens() {
            plantService.getGardenByUserId(this.$store.state.user.id)
                .then(response => {
                    this.userGardens = response.data;
                })
        },
        pushToGardenById() {
            
        }
    },

    created() {
        this.getUserGardens();
    }
};
</script>

<style scoped>
div {
    text-align: center;
}

fieldset {
    text-align: center;
}

button {
    width: 20vw;
}

.cardForm {
    width: 80vw;
}

.mandatory {
    background-color: rgb(241, 227, 227);
}

.cardImage {
    width: 80vw;
    height: auto;
    margin: 10px;
}
</style>
  