import { useRef } from "react";
import { fetchService } from "../services/fetchServices";
import { useNavigate } from "react-router";
import './AddPost.css';

const AddPost = (props) => {
    const newPostForm = useRef();

    const navigate = useNavigate();

    const PostHandler = async () => {
        const form = newPostForm.current;
        const data = {
            title: form['title'].value,
            content: form['content'].value,
            author: form['author'].value
        }

        fetchService.post("posts", data).then(() => {
            navigate('/posts')
        }).catch(error => {
            console.log(error)
        })
    }

    return (
        <div className="AddPost">
            <form ref={newPostForm}>
                <h1>Add a Post</h1>

                <label>Title</label>
                <input type="text"
                    name={'title'}
                />

                <label>Author</label>
                <input type="text"
                    name={'author'}
                />

                <label>Content</label>
                <input type="text"
                    name={'content'}
                />
            </form>
            <button onClick={PostHandler}>Add Post</button>
        </div>
    );

}

export default AddPost;