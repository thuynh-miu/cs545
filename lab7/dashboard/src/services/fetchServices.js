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

const getById = async (url, id) => {
    try {
        let data = {};
        data = (await API.get(`${url}/${id}`)).data;
        return data;
    } catch (error) {
        console.log(error.message)
        return {};
    }
}

const post = async (url, data) => {
    try {
        await API.post(url, data)
    } catch (error) {
        console.log(error.message)
    }
}

const deleteById = async (url, id) => {
    try {
        await API.delete(`${url}/${id}`);
    } catch (error) {
        console.log(error.message)
    }
}

export const fetchService = {
    get,
    getById,
    deleteById,
    post
};