import { useEffect, useState } from 'react'
import Posts from '../components/Posts'
import ChangeTitle from "../components/ChangeTitle"
import PostDetails from "../components/PostDetails"
import { fetchService } from "../services/fetchServices"
import AddPost from '../components/AddPost'

const Dashboard = () => {
    const [selectedState, setSelectedState] = useState(0)
    const [postsState, setPostsState] = useState([])
    const [postState, setPostState] = useState({
        title: "",
        author: ""
    })
    const [flag, setFlag] = useState(false)

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

    const setSelected = (id) => {
        setSelectedState(id)
        const post = postsState.find(post => post.id === id)
        setPostState(post)
    }

    const onChangeTitle = (newTitle) => {
        // Update postState with new title
        setPostState(prev => ({ ...prev, title: newTitle }));

        // Update postsState with the updated post
        setPostsState(prevPosts =>
            prevPosts.map(post =>
                post.id === selectedState ? { ...post, title: newTitle } : post
            )
        );
    };

    const deletePostClicked = (id) => {
        fetchService.deleteById("posts", id).then(() => {
            fetchPosts()
        }).catch(error => {
            console.log(error)
        })
    }

    const flagHandler = () => {
        setFlag(!flag)
    }

    useEffect(() => {
        fetchPosts()
    }, [flag])

    return (
        <div style={styles.dashboard}>
            <Posts posts={postsState}
                deletePost={deletePostClicked}
                setSelected={setSelected} />

            <ChangeTitle title={postState.title}
                onChangeTitle={onChangeTitle} />

            <PostDetails id={selectedState} />

            <AddPost click={flagHandler}></AddPost>
        </div>
    )
}

const styles = {
    dashboard: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    }
}

export default Dashboard;