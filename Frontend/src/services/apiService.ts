import { fetchWrapper } from "./fetchWrapper";
import { config } from "../config/config";
import { Building } from "./buildingRegisterInterface";
import { CreateUser } from "../pages/register/registerManager/interfaces";

const apiService = {
  getAllCountries: async () => {
    const countries = await fetchWrapper.get(config.api_all_countries);
    return countries;
  },

  registerManager: async (manager: CreateUser) => {
    try {
      const response = await fetchWrapper.post<CreateUser>(
        config.register_manager,
        manager
      );
      return response;
    } catch (error) {
      throw error;
    }
  },
  getManagedBuildings: async () => {
    const token: string | undefined =
      localStorage.getItem("token") || undefined;
    try {
      const response = await fetchWrapper.get(
        config.get_managed_buildings,
        token
      );
      return response;
    } catch (error) {
      throw error;
    }
  },
  getUnitByBuildingId: async () => {
    const buildingId = localStorage.getItem("buildingId");
    if (!buildingId) {
      throw new Error("No buildingId found in local storage");
    }
    try {
      const url = config.get_building_units(buildingId);
      const response = await fetchWrapper.get(url);
      return response;
    } catch (error) {
      throw error;
    }
  },
  addBuilding: async (building: Building) => {
    try {
      const response = await fetchWrapper.post<Building>(
        config.add_building,
        building
      );
      return response;
    } catch (error) {
      throw error;
    }
  },
};

export default apiService;
