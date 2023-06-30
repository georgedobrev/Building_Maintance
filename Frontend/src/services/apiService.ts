import { fetchWrapper } from "./callsWrapper";
import config from "../config/config.json"

const apiService = {
    getAllCountries: async () => {
        const countries = await fetchWrapper.get(config.api_all_countries);
        return countries;
    },

    registerManager: async (building) => {
        try {
            const response = await fetchWrapper.post(config.register_manager, building);
            return response;
        } catch (error) {
            throw error;
        }
    },
    addBuilding: async (building) => {
        try {
            const response = await fetchWrapper.post(config.add_building, building);
            return response;
        } catch (error) {
            throw error;
        }
    }
};

export default apiService;
