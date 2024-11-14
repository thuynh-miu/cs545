import { useEffect, useState } from 'react'
import Posts from '../components/Posts'
import ChangeTitle from "../components/ChangeTitle";
import PostDetails from "../components/PostDetails";
import { fetchService } from "../services/fetchServices";

const Dashboard = () => {
    const [selectedState, setSelectedState] = useState(0)
    const [postsState, setPostsState] = useState([
        { id: 111, title: 'Happiness', author: 'John' },
        { id: 112, title: 'MIU', author: 'Dean' },
        { id: 113, title: 'Enjoy Life', author: 'Jasmine' }
    ])
    const [postState, setPostState] = useState({
        title: "",
        author: ""
    })

    const fetchProducts = async () => {
        const posts = await fetchService.get("posts");
        console.log(posts)
        if (posts.length > 0) {
            setPostsState(posts);
            setSelectedState(posts[0].id);
        }
    }

    useEffect(() => {
        fetchProducts()
    }, [])

    const setSelected = (id) => {
        setSelectedState(id)
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

    return (
        <div style={styles.dashboard}>
            <Posts posts={postsState}
                setSelected={setSelected} />

            <ChangeTitle title={postState.title}
                onChangeTitle={onChangeTitle} />

            <PostDetails post={postState} />
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