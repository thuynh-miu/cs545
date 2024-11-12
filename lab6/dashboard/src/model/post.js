const posts = [
    { id: 111, title: 'Happiness', author: 'John'},
    { id: 112, title: 'MIU', author: 'Dean'},
    { id: 113, title: 'Enjoy Life', author: 'Jasmine'}
]

export default class PostModel {
    constructor(id, title, author) {
        this.id = id
        this.title = title
        this.author = author
    }

    static getAll() {
        return posts
    }

    static getById(id) {
        return posts.find(post => post.id === id)
    }

    static updateById(id, title) {
        const post = this.getById(id)
        if (post) {
            post.title = title
            return post
        }
    }
}