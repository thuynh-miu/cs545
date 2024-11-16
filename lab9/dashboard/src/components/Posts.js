import { useState, useEffect } from 'react'
import Post from './Post'
import { fetchService } from "../services/fetchServices"
import { Link } from 'react-router-dom'
import PostDetails from './PostDetails'

const Posts = (props) => {

    const [postsState, setPostsState] = useState([])

    const fetchPosts = async () => {
        fetchService.get("posts").then(posts => {
            setPostsState(posts)
        }).catch(error => {
            console.log(error)
        })
    }

    useEffect(() => {
        fetchPosts()
    }, [])

    const posts = postsState.map(post => {
        return (
            <Link to={`${post.id}`} key={post.id}>
                <Post
                    key={post.id}
                    id={post.id}
                    title={post.title}
                    author={post.author}
                    // deletePost={() => props.deletePost(post.id)}
                    // setSelected={() => props.setSelected(post.id)}
                />
            </Link>
        )
    })

    return (
        <div style={styles.container}>
            {posts}
            <PostDetails/>
        </div>
    )
}

const styles = {
    container: {
        display: 'flex',
        flexFlow: 'row wrap',
        justifyContent: 'center',
        width: '80%',
        margin: 'auto'
    }
}

export default Posts;