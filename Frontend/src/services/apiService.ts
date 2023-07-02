import { fetchWrapper } from "./fetchWrapper";
import { config } from "../config/config";
import { Building } from "./buildingRegisterInterface";
import { RegisterAdminData } from "./managerRegisterInterface";

const apiService = {
  getAllCountries: async () => {
    const countries = await fetchWrapper.get(config.api_all_countries);
    return countries;
  },

  registerManager: async (manager: RegisterAdminData) => {
    try {
      const response = await fetchWrapper.post(
        config.register_manager,
        manager
      );
      return response;
    } catch (error) {
      throw error;
    }
  },
  addBuilding: async (building: Building) => {
    try {
      const response = await fetchWrapper.post(config.add_building, building);
      return response;
    } catch (error) {
      throw error;
    }
  },
};

export default apiService;
