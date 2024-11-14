import { API } from "../config/api";

const get = async (url) => {
    try {
        let data = [];
        data = (await API.get(url)).data;
        return data;
    } catch (error) {
        console.log(error.message)
        return [];
    }
}

export const fetchService = {
    get
};