import { fetchUtils } from 'react-admin';
import { stringify } from 'query-string';

const apiUrl = '/api/v1/user';

const httpClient = (url, options = {}) => {
    if (!options.headers) {
        options.headers = new Headers({ Accept: 'application/json' });
    }
    const token = localStorage.getItem('token');
    options.headers.set('Authorization', `Bearer ${token}`);
    return fetchUtils.fetchJson(url, options);
}


function doDTO(json,f1,f2) {
	json[f1] = json[f2];
	delete json[f2];
	return json;
}

function actualToId(x,actual) {
	return doDTO(x,'id', actual);
}

function actualToIdDTO(json,actual) {
	return json.map(x => actualToId(x,actual));
}

/*
function idToActual(x,actual) {
	return doDTO(x,actual, 'id');
}

function idToActualDTO(json,actual) {
	return json.map(x => idToActual(x,actual));
}*/

const ProfileData = {
    getList: (resource, params) => {
        
		const url = `${apiUrl}/${resource}`;

        return httpClient(url).then(({ headers, json }) => ({
            data: actualToIdDTO(json,'employeeId'),
            total: json.length,
        }));
    },

    getOne: (resource, params) =>
        httpClient(`${apiUrl}/${resource}/${params.id}`).then(({ json }) => ({
            data: doDTO(json,'id', 'employeeId'),
        })),

    update: (resource, params) => {
		var putData = doDTO(params.data, 'employeeId', 'id')
        return httpClient(`${apiUrl}/${resource}/${params.id}`, {
            method: 'PUT',
            body: JSON.stringify(putData),
		}).then(({ json }) => ({ data: doDTO(putData, 'id', 'employeeId') }))
	},

    updateMany: (resource, params) => {
        const query = {
            filter: JSON.stringify({ id: params.ids}),
        };
        return httpClient(`${apiUrl}/${resource}?${stringify(query)}`, {
            method: 'PUT',
            body: JSON.stringify(params.data),
        }).then(({ json }) => { console.log(json); });
    },

    create: (resource, params) =>
        httpClient(`${apiUrl}/${resource}`, {
            method: 'POST',
            body: JSON.stringify([params.data]),
        }).then(({ json }) => ({
            data: doDTO(json[0],'id', 'employeeId'),
        })),

    delete: (resource, params) =>
        httpClient(`${apiUrl}/${resource}/${params.id}`, {
            method: 'DELETE',
        }).then(({ json }) => ({ data: json })),

    deleteMany: (resource, params) => {
        const query = {
            ids: params.ids.join(","),
        };
        return httpClient(`${apiUrl}/${resource}?${stringify(query)}`, {
            method: 'DELETE',
        }).then(({ json }) => ({ data: ['Done'] }));
    }
};

export default ProfileData;